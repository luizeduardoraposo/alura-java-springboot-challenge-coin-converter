package ritaja.xchangerate;

import java.math.BigDecimal;

import org.json.JSONException;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.alura.java.coin.converter.api.CurrencyConverter;
import com.alura.java.coin.converter.api.CurrencyConverterBuilder;
import com.alura.java.coin.converter.api.CurrencyNotSupportedException;
import com.alura.java.coin.converter.endpoint.EndpointException;
import com.alura.java.coin.converter.service.ServiceException;
import com.alura.java.coin.converter.storage.StorageException;
import com.alura.java.coin.converter.util.Currency;
import com.alura.java.coin.converter.util.Strategy;

/**
 * Created by rsengupta on 07/09/15.
 */
@Test
public class ConversionTest {
	private CurrencyConverter converter;

	@BeforeClass
	public void setup() {
		converter = new CurrencyConverterBuilder()
				.strategy(Strategy.YAHOO_FINANCE_FILESTORE)
				.accessKey("")
				.buildConverter();
		converter.setRefreshRateSeconds(86400);
	}

	@Test
	public void simpleConverterFunctionalityTest() throws ServiceException, StorageException, CurrencyNotSupportedException, EndpointException, JSONException {
		Assert.assertNotNull(converter.convertCurrency(new BigDecimal("100"), Currency.USD, Currency.EUR), "Expected a value after conversion");
	}

	@Test
	public void simpleConvertersionTest() throws ServiceException, StorageException, CurrencyNotSupportedException, EndpointException, JSONException {
		Assert.assertTrue(converter.convertCurrency(new BigDecimal("100"), Currency.USD, Currency.GBP).compareTo(new BigDecimal("100.00")) == -1, "Expected a value after conversion");
	}
}
