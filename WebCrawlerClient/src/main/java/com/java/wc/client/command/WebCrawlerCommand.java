package com.java.wc.client.command;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.java.wc.client.main.WebCrawlerClient;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * This class represents the command-line interface for the web crawler client.
 */

@Command(name = "wc_client", mixinStandardHelpOptions = true, helpCommand = true)
public class WebCrawlerCommand implements Callable<Integer> {

	Logger LOGGER = LogManager.getLogger(WebCrawlerCommand.class);

	@Option(names = { "-s", "--server" }, defaultValue = "", description = "Server Host", required = false)
	private String host;

	@Option(names = { "-p", "--port" }, defaultValue = "", description = "Server Port")
	private String port;

	@Option(names = { "-u", "--url" }, defaultValue = "", description = "URL to be crawled.")
	private String url;

	@Option(names = { "-d", "--depth" }, defaultValue = "", description = "depth of the URL crawl")
	private String depth;

	@Option(names = { "-i",
			"--is-domain-only" }, defaultValue = "true", description = "whether crawling to consider current domain of the URL only")
	private boolean isDomainOnly = false;

	@Option(names = { "-a",
			"--is-pretty-print" }, defaultValue = "", description = "whether crawling to consider current domain of the URL only")
	private boolean isPrettyPrint = false;

	@Option(names = { "-z", "--sleep-mode" }, defaultValue = "", description = "dry run")
	private boolean dryRun = false;

	@Option(names = { "-h", "--help" }, usageHelp = true, hidden = true)
	boolean helpRequested = false;

	/**
	 * Executes the web crawler command.
	 *
	 * @return the exit code
	 * @throws Exception if an error occurs during execution
	 */
	@Override
	public Integer call() throws Exception {

		if (dryRun) {
			LOGGER.info("Dry Run Mode");
			while (true) {
				Thread.sleep(10000);
			}
		}
		int depthValue, portValue;
		try {
			depthValue = Integer.parseInt(depth);
		} catch (Exception e) {
			LOGGER.error("depth should be an integer value");
			return 1;
		}

		try {
			portValue = Integer.parseInt(port);
		} catch (Exception e) {
			LOGGER.error("Port should be an integer value");
			return 1;
		}

		//System.out.println(" depthValue :: " + depthValue);
		//System.out.println(" portValue :: " + portValue);
		WebCrawlerClient wc = new WebCrawlerClient(depthValue, isDomainOnly, url, host, portValue);
		wc.setPrettyPrint(isPrettyPrint);
		wc.startCrawling();
		return 0;
	}

	/**
	 * The main method that starts the web crawler client.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {

		int rc = new CommandLine(new WebCrawlerCommand()).execute(args);
		System.exit(rc);
	}
}
