package util.event;

import org.jetbrains.annotations.NotNull;
import util.event.annotations.Cancellable;
import util.event.annotations.SubscribeEvent;
import util.event.annotations.EventBusSubscriber;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
/**
 * The EventBus class is responsible for managing the registration of event subscribers and the posting of events.
 * Subscribing to events is done by annotating classes and methods with the appropriate annotations:
 *  - {@link EventBusSubscriber} is used to mark event subscriber classes.
 *  - {@link SubscribeEvent} is used to mark event handler methods.
 *  - {@link Cancellable} is used to mark events that can be cancelled.
 *
 * The EventBus uses a priority queue to store events, which is sorted first by timestamp, and then by priority.
 * The priority of an event can be set using the priority attribute of {@link SubscribeEvent} annotation
 *
 * Usage:
 *  - Create an instance of the EventBus class.
 *  - Register event subscriber classes using the {@link #register(Object)} method.
 *  - Post events using the {@link #post(Object)} method.
 *  - Process events using the {@link #processEvents()} method.
 */

public class EventBus {
    // Map to store event subscribers
    private static Map<Class<?>, List<SubscriberMethod>> subscribers = new HashMap<>();

    // Priority queue to store events
    private PriorityQueue<Event> events = new PriorityQueue<>(Comparator.comparing(Event::getTimestamp).reversed().thenComparing(Event::getPriority));

    // Register a new subscriber
    /*public void register(@NotNull Object subscriber) {
        for (Method method : subscriber.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(SubscribeEvent.class)) {
                // Get the event class from the method's first parameter
                Class<?> eventClass = method.getParameterTypes()[0];
                // Get the priority of the event
                Priority priority = method.getAnnotation(SubscribeEvent.class).priority();
                // Create a new subscriber method
                SubscriberMethod subscriberMethod = new SubscriberMethod(subscriber, method, priority);
                // Add the subscriber method to the list for the event class
                subscribers.computeIfAbsent(eventClass, k -> new LinkedList<>()).add(subscriberMethod);
            }
        }
    }*/

    // Post a new event
    public void post(@NotNull Object event) {
        Class<?> eventClass = event.getClass();
        // Check if there are subscribers for the event
        if (subscribers.containsKey(eventClass)) {
            // Create a new event with the current timestamp
            Event eventWrapper = new Event(event, Instant.now(), eventClass.isAnnotationPresent(Cancellable.class));
            // Add the event to the queue
            events.add(eventWrapper);
        }
    }

    // Process events in the queue
    public void processEvents() {
        // Process events in the queue
        while (!events.isEmpty()) {
            Event eventWrapper = events.poll();
            // Check if the event is cancelled
            if (eventWrapper.isCancelled()) {
                continue;
            }
            // Get the list of subscribers for the event class
            List<SubscriberMethod> subscriberMethods = subscribers.get(eventWrapper.getEvent().getClass());
            // Iterate through the subscribers and call the subscribed methods
            for (SubscriberMethod subscriberMethod : subscriberMethods) {
                try {
                    subscriberMethod.getMethod().invoke(subscriberMethod.getSubscriber(), eventWrapper.getEvent());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Event wrapper class that contains the event and its timestamp
    public static class Event {
        private final Object event;
        private final Instant timestamp;
        private boolean cancelled;

        Event(Object event, Instant timestamp, boolean cancellable) {
            this.event = event;
            this.timestamp = timestamp;
            this.cancelled = !cancellable;
        }

        public Object getEvent() {
            return event;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public Priority getPriority() {
            return subscribers.get(event.getClass()).get(0).getPriority();
        }

        public boolean isCancelled() {
            return cancelled;
        }

        public void setCancelled(boolean cancelled) {
            this.cancelled = cancelled;
        }
    }

    // Subscriber method wrapper class
    private static class SubscriberMethod {
        private final Object subscriber;
        private final Method method;
        private final Priority priority;

        SubscriberMethod(Object subscriber, Method method, Priority priority) {
            this.subscriber = subscriber;
            this.method = method;
            this.priority = priority;
        }

        public Object getSubscriber() {
            return subscriber;
        }

        public Method getMethod() {
            return method;
        }

        public Priority getPriority() {
            return priority;
        }
    }
}