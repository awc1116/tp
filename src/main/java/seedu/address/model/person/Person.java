package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.other.Other;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Telegram telegram;
    private final Position position;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Skill> skills = new HashSet<>();
    private final Set<Other> others = new HashSet<>();
    private final List<Task> tasks;

    /**
     * Every field must be present and not null.
     */

    public Person(Name name, Phone phone, Email email, Telegram telegram, Position position, Address address,
                  Set<Tag> tags, Set<Skill> skills, Set<Other> others, List<Task> tasks) {
        requireAllNonNull(name, phone, email, telegram, position, address, tags, skills, others, tasks);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.position = position;
        this.address = address;
        this.tags.addAll(tags);
        this.skills.addAll(skills);
        this.others.addAll(others);
        this.tasks = new ArrayList<>(tasks);
    }

    // Legacy constructor
    /*public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this(name, phone, email, address, tags, "not started");
        this.tasks = new ArrayList<>(tasks);
    }*/

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public Position getPosition() {
        return position;
    }

    public Address getAddress() {
        return address;
    }

    public String getTaskStatus() {
        return this.tasks.stream().map(Task::getStatus).findFirst().orElse(TaskStatus.YET_TO_START).toString();
    }

    /**
     * Returns an immutable tag set.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Updates the task list.
     * Returns a new Person object with the updated tasks.
     */
    public Person updateTasks(List<Task> updatedTasks) {
        return new Person(name, phone, email, telegram, position, address, tags,
                skills, others, updatedTasks);
    }

    /**
     * Add a new task.
     * Returns a new Person object.
     */
    public Person addTask(Task newTask) {
        List<Task> updatedTasks = new ArrayList<>(tasks);
        updatedTasks.add(newTask);
        return new Person(name, phone, email, telegram, position, address, tags,
                skills, others, updatedTasks);
    }

    /**
     * Returns an immutable skill set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    /**
     * Returns an immutable other set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Other> getOthers() {
        return Collections.unmodifiableSet(others);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("telegram handle", telegram)
                .add("position", position)
                .add("address", address)
                .add("tags", tags)
                .add("skills", skills)
                .add("others", others)
                .toString();
    }

}
