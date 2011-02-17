package demo;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class FlexWebDriver {
	WebDriver webDriver;
	String flashObjectId;

	public FlexWebDriver(final WebDriver webDriver, final String flashObjectId) {
		this.webDriver = webDriver;
		this.flashObjectId = flashObjectId;
	}

	public String click(final String objectId, final String optionalButtonLabel) {
		return call("doFlexClick", objectId, optionalButtonLabel);
	}

	public String click(final String objectId) {
		return click(objectId, "");
	}

	// ... Omitted for clarity ...

	private String call(final String functionName, final String... args) {
		final Object result = ((JavascriptExecutor) webDriver).executeScript(
				makeJsFunction(functionName, args), new Object[0]);

		return result != null ? result.toString() : null;
	}

	private String makeJsFunction(final String functionName,
			final String... args) {
		final StringBuffer functionArgs = new StringBuffer();

		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				if (i > 0) {
					functionArgs.append(",");
				}
				functionArgs.append(String.format("'%1$s'", args[i]));
			}
		}
		return String.format("return document.%1$s.%2$s(%3$s);", flashObjectId,
				functionName, functionArgs);
	}
}