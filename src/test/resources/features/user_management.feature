Feature: Kullanıcı Yönetimi API Testleri

  Scenario: Kullanıcı listesi başarıyla getirilmeli
    Given API base URL ayarlanmıştır
    When kullanıcı "/users?page=2" endpoint'ine GET isteği gönderir
    Then response status code 200 olmalıdır
    And response "page" alanı 2 olmalıdır

  Scenario: Kullanıcı başarıyla güncellenmeli
    Given API base URL ayarlanmıştır
    When kullanıcı "/users/2" endpoint'ine "Seyma Upsated" ismiyle PUT isteği gönderir
    Then response status code 200 olmalıdır

  Scenario: Kullanıcı başarıyla silinmeli
    Given API base URL ayarlanmıştır
    When kullanıcı "/users/2" endpoint'ine DELETE isteği gönderir
    Then response status code 204 olmalıdır

  Scenario: Var olmayan kullanıcı sorgulandığında 404 dönmeli
    Given API base URL ayarlanmıştır
    When kullanıcı "/users/999" endpoint'ine GET isteği gönderir
    Then response status code 404 olmalıdır