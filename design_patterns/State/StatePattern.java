package design_patterns.State;

/**
 * Document Management System using State Pattern
 * Shows the lifecycle of a document from draft to publication
 */
public class StatePattern {
    
    /**
     * State interface defining all possible actions in any state
     */
    public interface State {
        void renderDoc();
        void draft();
        void review();
        void approve();
        void publish();
        String getStateName();
    }

    /**
     * Context class managing the current state and state transitions
     */
    public static class Document {
        private State currentState;
        private String content;
        private String author;

        public Document(String author) {
            this.author = author;
            this.content = "";
            // Initialize with Draft state
            this.currentState = new DraftState(this);
        }

        // State-specific operations
        public void renderDoc() {
            System.out.println("\nDocument Status:");
            System.out.println("Author: " + author);
            System.out.println("Current State: " + currentState.getStateName());
            currentState.renderDoc();
        }

        public void draft() {
            currentState.draft();
        }

        public void review() {
            currentState.review();
        }

        public void approve() {
            currentState.approve();
        }

        public void publish() {
            currentState.publish();
        }

        // Protected state transition method
        protected void changeState(State newState) {
            System.out.println("Transitioning from " + currentState.getStateName() + 
                             " to " + newState.getStateName());
            this.currentState = newState;
        }

        // Content management
        public void updateContent(String content) {
            this.content = content;
            System.out.println("Content updated");
        }

        public String getContent() {
            return content;
        }
    }

    /**
     * Draft State - Initial state of any document
     */
    public static class DraftState implements State {
        private final Document document;

        public DraftState(Document document) {
            this.document = document;
        }

        @Override
        public void renderDoc() {
            System.out.println("Rendering document in draft mode");
            System.out.println("Status: Editable");
        }

        @Override
        public void draft() {
            System.out.println("Already in draft state");
        }

        @Override
        public void review() {
            document.changeState(new ReviewState(document));
        }

        @Override
        public void approve() {
            System.out.println("Cannot approve draft directly. Send for review first.");
        }

        @Override
        public void publish() {
            System.out.println("Cannot publish draft directly. Need review and approval first.");
        }

        @Override
        public String getStateName() {
            return "Draft";
        }
    }

    /**
     * Review State - Document is under review
     */
    public static class ReviewState implements State {
        private final Document document;

        public ReviewState(Document document) {
            this.document = document;
        }

        @Override
        public void renderDoc() {
            System.out.println("Rendering document in review mode");
            System.out.println("Status: Under Review");
        }

        @Override
        public void draft() {
            document.changeState(new DraftState(document));
        }

        @Override
        public void review() {
            System.out.println("Document is already under review");
        }

        @Override
        public void approve() {
            document.changeState(new ApprovedState(document));
        }

        @Override
        public void publish() {
            System.out.println("Cannot publish without approval");
        }

        @Override
        public String getStateName() {
            return "Under Review";
        }
    }

    /**
     * Approved State - Document has been approved
     */
    public static class ApprovedState implements State {
        private final Document document;

        public ApprovedState(Document document) {
            this.document = document;
        }

        @Override
        public void renderDoc() {
            System.out.println("Rendering approved document");
            System.out.println("Status: Approved, Ready for Publication");
        }

        @Override
        public void draft() {
            document.changeState(new DraftState(document));
        }

        @Override
        public void review() {
            System.out.println("Document is already approved");
        }

        @Override
        public void approve() {
            System.out.println("Document is already approved");
        }

        @Override
        public void publish() {
            document.changeState(new PublishedState(document));
        }

        @Override
        public String getStateName() {
            return "Approved";
        }
    }

    /**
     * Published State - Final state of the document
     */
    public static class PublishedState implements State {
        private final Document document;

        public PublishedState(Document document) {
            this.document = document;
        }

        @Override
        public void renderDoc() {
            System.out.println("Rendering published document");
            System.out.println("Status: Published");
        }

        @Override
        public void draft() {
            System.out.println("Creating new draft version");
            document.changeState(new DraftState(document));
        }

        @Override
        public void review() {
            System.out.println("Document is already published");
        }

        @Override
        public void approve() {
            System.out.println("Document is already published");
        }

        @Override
        public void publish() {
            System.out.println("Document is already published");
        }

        @Override
        public String getStateName() {
            return "Published";
        }
    }

    public static void main(String[] args) {
        // Create a new document
        Document doc = new Document("John Doe");
        
        // Demonstrate the document lifecycle
        System.out.println("=== Document Lifecycle Demo ===");
        
        // Initial state
        doc.renderDoc();
        doc.updateContent("Initial draft content");
        
        // Try invalid transition
        doc.publish(); // Should fail
        
        // Proper flow
        doc.review();
        doc.renderDoc();
        
        doc.approve();
        doc.renderDoc();
        
        doc.publish();
        doc.renderDoc();
        
        // Try modifying published document
        doc.review(); // Should show appropriate message
        
        // Create new draft
        doc.draft();
        doc.renderDoc();
    }
}

