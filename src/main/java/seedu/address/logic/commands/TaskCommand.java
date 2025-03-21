package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DUE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESC;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Adds a task to a specified team member identified by an index number used in the displayed person list.
 */
public class TaskCommand extends Command {
    public static final String COMMAND_WORD = "task";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the specified team member.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TASK_DESC + "TASK_DESCRIPTION\n"
            + "[ " + PREFIX_DUE_DATE + " yyyy-mm-dd HH:mm]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_DESC + " Complete project milestone "
            + PREFIX_DUE_DATE + "2025-05-05 12:00";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "Added task to member: %1$s";

    private final Index index;
    private final Task task;

    /**
     * Creates a TaskCommand to add the specified {@code Task} to the team member at the specified {@code Index}.
     * @param index Index of the team member in the displayed person list.
     * @param task Task to be added to the team member.
     */
    public TaskCommand(Index index, Task task) {
        requireNonNull(index);
        requireNonNull(task);
        this.index = index;
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person updatedPerson = personToEdit.addTask(task);

        model.setPerson(personToEdit, updatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, updatedPerson.getName()));
    }
}
