package hu.dmlab.app;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.google.common.base.Stopwatch;

public class App {

	private static final String RUN_COMMAND = "java -cp \"bin:lib/*\" hu.dmlab.app.App --output output_folder --input input_file";
	private static final String OPTIONS_INPUT = "i";
	private static final String OPTIONS_OUTPUT = "o";

	public static void main(String[] args) {
		Stopwatch watch = Stopwatch.createStarted();
		CommandLineParser parser = new GnuParser();
		Options options = getOptions();
		String outputPath = null, inputPath = null;
		try {
			CommandLine commandLine = parser.parse(options, args);
			outputPath = commandLine.getOptionValue(OPTIONS_OUTPUT);
			inputPath = commandLine.getOptionValue(OPTIONS_INPUT);
		} catch (Exception e) {
			printHelp(options);
			return;
		}
		File outputFolder = new File(outputPath);
		if (outputPath == null || outputPath.equals("")) {
			printHelp(options);
			return;
		}
		outputFolder.mkdirs();
		File inputFile = new File(inputPath);
		if (inputPath == null || inputPath.equals("") || !inputFile.exists()) {
			printHelp(options);
			return;
		}
		App app = new App();
		app.process(inputFile, outputFolder);
		watch.stop();
	}

	private void process(File inputFile, File outputFile) {
		throw new UnsupportedOperationException("Not implemented yet.");

	}

	private static void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(RUN_COMMAND, options);
	}

	// Due to bad design
	@SuppressWarnings("static-access")
	private static Options getOptions() {
		Options options = new Options();
		Option o;
		o = OptionBuilder.hasOptionalArgs().withLongOpt("input").withDescription("Input file").create(OPTIONS_INPUT);
		options.addOption(o);
		o = OptionBuilder.hasOptionalArgs().withLongOpt("output").withDescription("Output path").create(OPTIONS_OUTPUT);
		options.addOption(o);
		return options;
	}

}
