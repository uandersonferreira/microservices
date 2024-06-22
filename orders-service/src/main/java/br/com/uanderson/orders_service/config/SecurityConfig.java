package br.com.uanderson.orders_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define um bean que cria a configuração do filtro de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Desabilita a proteção CSRF (Cross-Site Request Forgery)
        http.csrf(AbstractHttpConfigurer::disable);

        // Configura a correspondência de segurança para todas as requisições (/**)
        // e exige autenticação para qualquer requisição
        http.securityMatcher("/**").authorizeHttpRequests(
                        auth -> auth.anyRequest().authenticated()
                )
                // Configura o servidor de recursos OAuth2 para usar JWT para autenticação
                .oauth2ResourceServer(configure -> configure.jwt(
                        jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter())
                ));

        // Constrói e retorna a cadeia de filtros de segurança configurada
        return http.build();
    }

    // Define um conversor de JWT para AbstractAuthenticationToken
    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthConverter() {
        // Cria um conversor de autenticação JWT
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // Define um conversor personalizado de autoridades do JWT
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());

        return converter;
    }

    // Classe interna que converte JWT em uma coleção de GrantedAuthority
    @SuppressWarnings("unchecked")
    private static class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            // Retorna uma lista vazia se não houver reivindicações no JWT
            if (jwt.getClaims() == null) return List.of();

            // Obtém o mapa de acesso ao reino das reivindicações do JWT
            Map<String, List<String>> realmAccess = Collections
                    .unmodifiableMap((Map<String, List<String>>) jwt.getClaims().get("realm_access"));

            // Converte os nomes dos papéis em GrantedAuthority e os coleta em uma lista
            return realmAccess.get("roles").stream()
                    .map(roleName -> "ROLE_" + roleName)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
    } // classe interna KeycloakRealmRoleConverter
} // classe SecurityConfig


/*
Collections.unmodifiableMap:

1. Retorna uma visualização não modificável do mapa especificado.
    - `Collections.unmodifiableMap` cria uma versão do mapa fornecido que não
     pode ser alterada. Qualquer tentativa de modificar a visualização do mapa
     resultará em uma exceção.

2. As operações de consulta no mapa retornado "leem" ("read through" ) para o mapa
especificado e as tentativas de modificar o mapa retornado, seja diretamente ou por
meio de suas visualizações de coleção, resultam em uma UnsupportedOperationException.
    - Isso significa que, enquanto você pode acessar os dados no mapa
     (por exemplo, ler valores, iterar sobre entradas), qualquer tentativa de
      alteração (como adicionar ou remover entradas) lançará
      uma `UnsupportedOperationException`.

3. O mapa retornado será serializável se o mapa especificado for serializável.
    - Se o mapa original fornecido a `Collections.unmodifiableMap` for
    serializável, então a versão não modificável do mapa também será serializável.

 */

