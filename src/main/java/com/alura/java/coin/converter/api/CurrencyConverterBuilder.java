package com.alura.java.coin.converter.api;

import com.alura.java.coin.converter.util.Strategy;
import com.alura.java.coin.converter.endpoint.CurrencyLayerEndpoint;
import com.alura.java.coin.converter.endpoint.YahooEndpoint;
import com.alura.java.coin.converter.storage.DiskStore;
import com.alura.java.coin.converter.storage.FileStore;

/**
 * Created by rsengupta on 06/09/15.
 */
public class CurrencyConverterBuilder {
	private String _accessKey;
	private Strategy _strategy;
	private DiskStore diskStore;

	public CurrencyConverterBuilder() {
	}

	public CurrencyConverter buildConverter() {
		if (_strategy == null) {
			throw new IllegalArgumentException("No Strategy defined to create Currency converter");
		} else if (_strategy.equals(Strategy.YAHOO_FINANCE_FILESTORE)) {
			diskStore = new FileStore("yahoo");
			return new CurrencyConverter(diskStore, new YahooEndpoint(diskStore));
		} else if (_strategy.equals(Strategy.CURRENCY_LAYER_FILESTORE) && _accessKey != null) {
			diskStore = new FileStore("currencyLayer");
			return new CurrencyConverter(diskStore, new CurrencyLayerEndpoint(diskStore, _accessKey));
		}
		throw new IllegalArgumentException("Strategy: " + _strategy + " requires accessKey for endpoint service, none provided!");
	}

	public CurrencyConverterBuilder strategy(Strategy _strategy) {
		this._strategy = _strategy;
		return this;
	}

	public CurrencyConverterBuilder accessKey(String _accessKey) {
		this._accessKey = _accessKey;
		return this;
	}
}
