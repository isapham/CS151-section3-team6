package application.tetris.logic.events;

/** 
 * This class is for capture the move event
 *
 */
public final class MoveEvent {
	private final EventType eventType;
    private final EventSource eventSource;

    public MoveEvent(EventType eventType, EventSource eventSource) {
        this.eventType = eventType;
        this.eventSource = eventSource;
    }

    /**
     * This method is for getting event type: up, down, left, right
     * @return
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * This method is for getting event source
     * @return
     */
    public EventSource getEventSource() {
        return eventSource;
    }
}
