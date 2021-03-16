package com.lucasilva.dryve.utils;

import java.net.URI;

import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.lucasilva.dryve.dto.KBB;
import com.lucasilva.dryve.enums.ErroType;
import com.lucasilva.dryve.service.exceptions.RequestPriceKbbException;

@Component
public class RequestUtils {

	public KBB getPriceKBB(String id) {
		String url = "https://6048bdf1fb5dcc0017968e3f.mockapi.io/api/v1/kbb/prices/" + id;
		RestTemplate template = new RestTemplate();
		try {
			RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(url)).build();
			return template.exchange(requestEntity , KBB.class).getBody();
		} catch (RestClientException e) {
			throw new RequestPriceKbbException(ErroType.ERROR_CONSULT_PRICE_KBB.toString());
		}
	}
}