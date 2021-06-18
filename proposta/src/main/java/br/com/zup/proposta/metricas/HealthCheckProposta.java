package br.com.zup.proposta.metricas;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckProposta implements HealthIndicator {

	@Value("${health.proposta.url}")
	private String urlSaude;
	
	@Value("${health.proposta.porta}")
	private int porta;

	@Override
	public Health health() {
		Map<String, String> details = new HashMap<>();
		details.put("Api Externa", "API Análise de Propostas");
		details.put("URL", urlSaude+"/api/solicitacao");
		try (Socket socket = new Socket(new java.net.URL(urlSaude+"/api/solicitacao").getHost(), porta)) {
        } catch (Exception e) {
            return Health.down().withDetail("error", e.getMessage()).build();
        }
        return Health.up().withDetails(details).build();
    }
}
