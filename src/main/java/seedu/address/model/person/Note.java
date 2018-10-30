package seedu.address.model.person;

/**
 * Represents a Person's note in the address book.
 */
public class Note {

    public static final String MESSAGE_NOTE_CONSTRAINTS = "Note should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NOTE_VALIDATION_REGEX = "[\\p{Alnum}](.*)";

    // Edit this to change what a text checks for before deciding to add a full stop.
    private static final String END_OF_SENTENCE_REGEX = "[.!?]";

    /**
     * Checks if note ends with a character from {@code END_OF_SENTENCE_REGEX}.
     */
    private static final String NOTE_PUNCTUATION_REGEX = "(.*)" + END_OF_SENTENCE_REGEX;

    // Text in an unedited or reset note
    private static final String DEFAULT_NOTE = "<No note added>";

    private String text;

    /**
     * Constructs a {@code Note} with default text.
     */
    public Note() {
        text = DEFAULT_NOTE;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && text.equals(((Note) other).text)); // state check
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    /**
     * Adds text to this note.
     * @param text to be added
     */
    void add(String text) {
        // Clear default text if default
        if (this.isDefault()) {
            this.text = "";
        }
        // if current text ends with one of the characters in {@code END_OF_SENTENCE_REGEX}, change to comma
        if (this.text.matches(NOTE_PUNCTUATION_REGEX)) {
            this.text = this.text.split(END_OF_SENTENCE_REGEX)[0];
            this.text += ",";
        }
        /*
         * If added text does not end with one of the characters in {@code END_OF_SENTENCE_REGEX},
         * add a full stop to end.
         */
        if (!text.matches(NOTE_PUNCTUATION_REGEX)) {
            text += ".";
        }
        this.text += text;
    }

    /**
     * Resets text to {@code DEFAULT_NOTE}.
     */
    void delete() {
        this.text = DEFAULT_NOTE;
    }

    /**
     * Checks if this note has the default text.
     */
    boolean isDefault() {
        return this.text.equals(DEFAULT_NOTE);
    }
}
