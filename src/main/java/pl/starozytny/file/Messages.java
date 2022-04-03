package pl.starozytny.file;

import org.mineacademy.fo.settings.SimpleLocalization;

import java.util.List;

public class Messages extends SimpleLocalization {

	@Override
	protected int getConfigVersion() {
		return 1;
	}


	public static class Information {
		public static List<String> INFORM_STAFF_ADD;
		public static List<String> INFORM_REMOVE;
		public static List<String> INFORM_PLAYER_ADD;
		public static List<String> INFORM_PLAYER_LOGOUT;
		public static List<String> INFORM_SERVER_PRZYZNAJ;
		public static List<String> INFORM_SERVER_BAN;

		private static void init() {

			pathPrefix("Information");
			INFORM_STAFF_ADD = getStringList("INFORM_STAFF_ADD");
			INFORM_REMOVE = getStringList("INFORM_REMOVE");
			INFORM_PLAYER_ADD = getStringList("INFORM_PLAYER_ADD");
			INFORM_PLAYER_LOGOUT = getStringList("INFORM_PLAYER_LOGOUT");
			INFORM_SERVER_PRZYZNAJ = getStringList("INFORM_SERVER_PRZYZNAJ");
			INFORM_SERVER_BAN = getStringList("INFORM_SERVER_BAN");

		}
	}

	public static class Error {
		public static String PLAYER_OFFLINE;
		public static String PLAYER_IS_NOW_GEING_CHECKED;
		public static String PLAYER_IS_NO_CHECKED;
		public static String NO_PERMISSION;
		public static String COMMAND_BLOCKED;
		public static String MISSING_PLAYER_NAME;

		private static void init() {

			pathPrefix("Error");
			PLAYER_OFFLINE = getString("PLAYER_OFFLINE");
			PLAYER_IS_NOW_GEING_CHECKED = getString("PLAYER_IS_NOW_GEING_CHECKED");
			PLAYER_IS_NO_CHECKED = getString("PLAYER_IS_NO_CHECKED");
			NO_PERMISSION = getString("NO_PERMISSION");
			COMMAND_BLOCKED = getString("COMMAND_BLOCKED");
			MISSING_PLAYER_NAME = getString("MISSING_PLAYER_NAME");

		}
	}

}
