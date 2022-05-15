

import controller.ProfileMenuController;
import controller.ProgramController;
import model.Database;
import model.User;
import net.bytebuddy.implementation.auxiliary.MethodCallProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OverallTest {

	@Test
	void testGameRun() {
		InputStream sysInBackup = System.in; // backup System.in to restore it later
		ByteArrayInputStream in = new ByteArrayInputStream((
				"user create -u ali -p safari -n amoo\n" +
						"user create -u amnam -p amnam -n amnam\n" +
						"user login -u ali -p safari\n" +
						"play game -p1 ali -p2 amnam\n" +
						"get researchable techs\n" +
						"add technology -n Agriculture\n" +
						"add technology -n Agriculture\n" +
						"get researchable techs\n" +
						"add technology -n Animal_Husbandry\n" +
						"add technology -n Archery\n" +
						"add technology -n Mining\n" +
						"add technology -n Mining\n" +
						"get researchable techs\n" +
						"get current research\n" +
						"research -n Pottery\n" +
						"get current research\n" +
						"add beaker -a 10\n" +
						"get current research\n" +
						"add beaker -a 30\n" +
						"get current research\n" +
						"get researchable techs\n" +
						"research -n Masonry\n" +
						"get current research\n" +
						"add beaker -a 10\n" +
						"change research -n Calendar\n" +
						"get current research\n" +
						"change research -n Masonry\n" +
						"get current research\n" +
						"spawn unit -p 70 -n Tank\n" +
						"spawn unit -p 95 -n panzer\n" +
						"spawn unit -p 98 -n infantry\n" +
						"map show -p 75\n" +
						"map show -p 70\n" +
						"select unit armed -p 70\n" +
						"unit moveto -p 93\n" +
						"unit moveto -p 117\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"unit moveto -p 75\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"spawn unit -p 77 -n Settler\n" +
						"select unit civilian -p 77\n" +
						"unit info\n" +
						"unit found city\n" +
						"spawn unit -p 76 -n Worker\n" +
						"select unit civilian -p 76\n" +
						"unit action list\n" +
						"improvement build -n Miner\n" +
						"improvement build -n Mine\n" +
						"cheat next turn\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"unit info\n" +
						"map show -p 75\n" +
						"spawn unit -p 123 -n Settler\n" +
						"select unit civilian -p 123\n" +
						"map show -p 123\n" +
						"unit info\n" +
						"unit found city\n" +
						"map show -p 75\n" +
						"select unit armed -p 98\n" +
						"unit info\n" +
						"unit fortify heal\n" +
						"damage unit -a 4\n" +
						"unit fortify heal\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"select unit civilian -p 76\n" +
						"unit moveto -p 124\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"map show -p 124\n" +
						"unit info\n" +
						"unit action list\n" +
						"unit pillage\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"map show -p 76\n" +
						"unit repair\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"map show -p 76\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"map show -p 76\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"map show -p 76\n" +
						"unit road build\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"tile info -p 76\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"tile info -p 76\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"tile info -p 76\n" +
						"unit info\n" +
						"unit route remove\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"tile info -p 76\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"tile info -p 76\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"tile info -p 76\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"tile info -p 76\n" +
						"unit info\n" +
						"select unit armed -p 75\n" +
						"unit info\n" +
						"unit alert\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"spawn unit -p 76 -n Warrior\n" +
						"cheat next turn\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"unit info\n" +
						"unit moveto -p 98\n" +
						"unit info\n" +
						"select city --name Karakorum\n" +
						"city attack --target 76\n" +
						"select unit armed -p 76\n" +
						"unit info\n" +
						"cheat next turn\n" +
						"cheat next turn\n" +
						"menu exit\n" + "menu exit\n" + "menu exit\n").getBytes());
		System.setIn(in);
		Main.main(new String[]{});
		System.setIn(sysInBackup);
	}

}
