Feature: Hava durumu Api'si kullanarak parametre olarak verilen sehir ve gün bilgilerine uygun hava durumunu alma

    Scenario Outline: API ile hava durumu tahmini alma
      When <sehir> <saat> bilgilerine göre request atildiginda
      Then Dort gunluk hava durumu tahmini gelmis olmalidir
      Examples:
        | sehir    | saat |
        | "edirne" |  32  |

  Scenario Outline: Google'dan sehre ve gune uygun hava durumu bilgisini alma
    Given google.com sitesine gidilmis olmalidir
    When Arama kismina <sehir> hava durumu yazildigi zaman
    And <gun>. gunun hava durumuna bakildiginda
    Then Uygun hava durumu gelmis olmalidir
    Examples:
      | sehir    | gun |
      | "edirne" |  4  |
