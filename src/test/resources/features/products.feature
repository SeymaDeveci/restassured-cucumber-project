Feature: Ürün Sorgulama API Testleri

  Scenario: Var olmayan ürün sorgulandığında 404 dönmeli
    Given DummyJSON base URL ayarlanmıştır
    When kullanıcı "/products/9999" endpoint'ine GET isteği gönderir
    Then response status code 404 olmalıdır

  Scenario: Çok fazla istek gönderildiğinde rate limit uygulanmalı
    Given DummyJSON base URL ayarlanmıştır
    When kullanıcı "/products/1" endpoint'ine art arda 30 istek gönderir
    Then tüm isteklerdeki response status code 429 olmalıdır

  Scenario: Ürün response'u beklenen şemaya uygun olmalı
    Given DummyJSON base URL ayarlanmıştır
    When kullanıcı "/products/1" endpoint'ine GET isteği gönderir
    Then response şemaya uygun olmalıdır