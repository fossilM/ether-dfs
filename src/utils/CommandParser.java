package utils;

public class CommandParser {

	enum Command {

		LS(1), CD(2), CAT(2), NANO(2), PWD(1), MKDIR(2), RM(2), FIND(2), TIME(2), LSM(1), MEM(1), HELP(1);

		private int argsNum;

		Command(int argsNum) {
			this.argsNum = argsNum;
		}

		public int argsNum() {
			return argsNum;
		}

		public boolean isValid(String[] cmds) {
			if (cmds.length == this.argsNum())
				return true;
			else
				return false;
		}

	}

	public static String parse(String[] cmds) {
		try {
			Command cmd = Command.valueOf(cmds[0].toUpperCase());
			if (cmds.length < cmd.argsNum()) {
				System.out.println("Not enough arguments");
			}
			if (cmds.length > cmd.argsNum()) {
				System.out.println("Too many arguments");
			}
			if (cmd.isValid(cmds))
				return cmd.toString();
			else
				return "";
		} catch (IllegalArgumentException e) {
			return "Operation does not exist!";
		}
	}

}
