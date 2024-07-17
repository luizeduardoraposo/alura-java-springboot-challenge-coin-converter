package com.alura.java.coin.converter.api;

import java.math.BigDecimal;

import com.alura.java.coin.converter.util.Currency;
import org.json.JSONException;

import com.alura.java.coin.converter.endpoint.EndpointException;
import com.alura.java.coin.converter.service.ServiceException;
import com.alura.java.coin.converter.storage.StorageException;

public interface Converter {

	public BigDecimal convertCurrency(BigDecimal moneyAmount, Currency fromCurrency, Currency toCurrency) throws CurrencyNotSupportedException, JSONException, StorageException, EndpointException, ServiceException;
}
