package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARK;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttendanceCommand;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Mark;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class AttendanceCommandParser implements Parser<AttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns an MarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AttendanceCommand parse(String args) throws ParseException {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_ATTENDANCE);

            if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_ATTENDANCE)
                    || argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE)
                );
            }

            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            Index attendanceIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ID).get());
            Mark attendanceMark = ParserUtil.parseMark(argMultimap.getValue(PREFIX_MARK).get());

            return new AttendanceCommand(index, attendanceIndex, attendanceMark);
        }

        /**
         * Returns true if none of the prefixes contains empty {@code Optional} values in the given
         * {@code ArgumentMultimap}.
         */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}



