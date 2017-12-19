package antiSpamFilter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import antiSpamFilter.utils.Rule;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class AntiSpamFilterTest {

	AntiSpamFilter filter = AntiSpamFilter.getInstance();

	@Before
	public void initializePaths() {
		filter.getGUI()
				.setHamPath(System.getProperty("user.dir") + "\\AntiSpamConfigurationForProfessionalMailbox\\ham.log");
		filter.getGUI().setSpamPath(
				System.getProperty("user.dir") + "\\AntiSpamConfigurationForProfessionalMailbox\\spam.log");
		filter.getGUI().setRulesPath(
				System.getProperty("user.dir") + "\\AntiSpamConfigurationForProfessionalMailbox\\rules.cf");
		filter.loadRules();
	}

	@Test
	public void testGetInstance() {
		assertNotNull(filter);
	}

	@Test
	public void testLoadRulesDefault() throws IOException {
		File file = new File(System.getProperty("user.dir") + "jUnitTests\\DeafultRules.cf");
		filter.loadRules();
		assertEquals(filter.countRules(), filter.getGUI().getManualRules().size());
		
		RandomAccessFile f = new RandomAccessFile(filter.getGUI().getRulesPath(), "rw");
		f.getChannel().lock();
		filter.loadRules();
		f.close();
		
	}

	@Test
	public void testGetGUI() {
		assertNotNull(filter.getGUI());
	}

	@Test
	public void testCountRules() {
		assertEquals(335, filter.countRules());
	}

	@Test
	public void testEvaluateHam() {
		filter.getGUI().setHamPath(
				System.getProperty("user.dir") + "\\AntiSpamConfigurationForProfessionalMailbox\\ham__.log");
		filter.evaluateHam(filter.getGUI().getManualRules());
	}

	@Test
	public void testEvaluateSpamFileNotFound() {
		filter.getGUI().setSpamPath(
				System.getProperty("user.dir") + "\\AntiSpamConfigurationForProfessionalMailbox\\spam__.log");
		filter.evaluateSpam(filter.getGUI().getManualRules());
	}

	@Test
	public void testRunManual() {
		ArrayList<Rule> rules = new ArrayList<Rule>();
		for (Rule r : filter.getGUI().getManualRules()) {
			r.setWeight(new Random().nextDouble());
			rules.add(r);
		}
		filter.getGUI().setManualRules(rules);
		filter.runManual();
		assertNotEquals(-1, filter.getGUI().getFN());
		assertNotEquals(-1, filter.getGUI().getFP());
	}

	@Test
	public void testSaveResults() throws IOException {
		filter.saveResults(true);
		File auto = new File(filter.getGUI().getRulesPath());
		assertTrue(auto.isFile());
		filter.saveResults(false);
		File manual = new File(filter.getGUI().getRulesPath());
		assertTrue(manual.isFile());
	}

	@Test
	public void testCheckSolutions() throws IOException {
		assertEquals(filter.countRules(), filter.checkSolutions().length);
		RandomAccessFile f = new RandomAccessFile(
				"experimentBaseDirectory\\AntiSpamStudy\\data\\NSGAII\\AntiSpamFilterProblem\\BEST_HV_VAR.tsv", "rw");
		f.getChannel().lock();
		filter.checkSolutions();
		f.close();
	}

	@Test
	public void testRunAuto() {
		assertNotEquals(0, filter.getGUI().getIndependentRuns());
		assertEquals(335, filter.getGUI().getAutoRules().size());

		filter.runAuto();
	}

    @After
	public void tessStart(){
	    filter.getGUI().Start.doClick();
        filter.getGUI().AutoRadio.doClick();
        filter.getGUI().Start.doClick();
        filter.getGUI().ManualRadio.doClick();
        filter.getGUI().Start.doClick();
    }

	@Test
	public void testGenerator(){
		filter.getGUI().GenerateRandom.doClick();
	}
}
