package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddBookingCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ClearAllCommand;
import seedu.address.logic.commands.ClearBookingsCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteBookingCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditBookingCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterBookingsCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListBookingCommand;
import seedu.address.logic.commands.ListPersonsCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.TodayCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddPersonCommand.COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

        case AddBookingCommand.COMMAND_WORD:
            return new AddBookingCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case EditBookingCommand.COMMAND_WORD:
            return new EditBookingCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case DeleteBookingCommand.COMMAND_WORD:
            return new DeleteBookingCommandParser().parse(arguments);

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindPersonsCommandParser().parse(arguments);

        case FilterBookingsCommand.COMMAND_WORD:
            return new FilterBookingsCommandParser().parse(arguments);

        case ListPersonsCommand.COMMAND_WORD:
            return new ListPersonsCommand();

        case ListBookingCommand.COMMAND_WORD:
            return new ListBookingCommandParser().parse(arguments);

        case ClearAllCommand.COMMAND_WORD:
            return new ClearAllCommand();

        case ClearBookingsCommand.COMMAND_WORD:
            return new ClearBookingsCommand();

        case TodayCommand.COMMAND_WORD:
            return new TodayCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
