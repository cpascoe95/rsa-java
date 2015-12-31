package uk.co.cpascoe.rsa;

import org.junit.Test;
import static org.junit.Assert.*;
import java.security.SecureRandom;

public class RsaProviderTests {
    private static final String PRIVATE_KEY_JSON = "{\"d\":\"51232FB73E357836E664AC91E743D224485B0C3CDC420EC47D539104341DB01CB685CDA2CB9CF17543BA1F0D27411591E30E17F8F72FB04A681911FD6BC0F7A024D781B1A31A91DAFD4561F357C1DAF89666FF9681698288E92F6BF02F3A0102BBE100F6F19237351C48E5CBCB97E0EAD75C91FE0685AC1161EAB7E8058E4ED87EA1DD4DB7F7005F843E649E0F224F3B3A3EE9DBA298791DFF2D7E4A0E7D6215EE58506713C9BAA48A332AC617F51FD3D44D98FF7F4AEBD912DF0C4110269665994394763396B5CBB9888851BB16531B4CF320B5EBB578EA58D2C8B780D4A220BF0F1B62B633BDF6D50E96D4BFE01B0592DA13F22CEF0F2E99E901250B5B1F46\",\"e\":\"01000100\",\"q\":\"49C6C7F990CF9189C6B6E50B27B6C62C5EE5FF6675019686BEB6F198AF4D294E37D479F1780B2F18B54EEEBC092594C6794B5289E9BE092BCDDCEDA78CB164E087383F018D91583934218F8413848C5B3A4ABD54E66EF3BDC445D0E6EB5ABE23E551D8D11A4946343B8CF76256D99333896AA134C57996BCC88787CDDC49BCCC\",\"p\":\"03BEC02FFFF40223BE213DBDC751EC7AB78579B9FB675D717535051AFC62E8FFD3D9E2E52FE7E58D93B2E792D4A9170A208470D7EE4F28BDD9775C6575C1E62FF37FA95453CB1B09B04F8EF6C585BE075DAB36778D0C5302102AF585A4D8AC50B3F658831636E0948D5741AED583D91F177A87331E15C33CF19034270873239F\",\"n\":\"DB8041500822E8DD393717E4107FA18179D7851BE0E6795944013B9C27FDD12CA337E1018568D64D6CF8EEEDF585B32AF00B6FC68A20BAF25D87F29FAD3C2AF684CCF47A8C5CA39289E89CF144C5369CC0022262BE03E37FD2BD917857EF52BB2F7B7DA17EB9D872EDD56C1A87C48F99040DF107EF1A8C6A6537FB30A17DCFAD1E07F526161502B5729218DFA849ADAF36B834C6F3B4CD1B03A9565DDB1201275FC5BBB7C8BDF12C0AF1FE0A3F3B1F1F4B71C594A010CA4B2C557A5D0C331E88F02A59BD44A1C675F7ECFABD33FE3BB455AE1B08A92D2C2D26841A26B76F6E8C8AC2D21AB24482F4875180F05FFD05BDA8C3381E82F05D07827F42579D4B457F\",\"phi_n\":\"90FCB826785D5331B55EF41A2277EED9636C0CFB6E7D8661101544E97B4CC0DE9789842ADC75C1A723F7189E17B7075A563CAC65B211880AB732A892ABC9DEE509140C25ACFF2E50A5777F766BBBEB38290D2E964A889CBFFD4DCC0BC7BBE74697324C4C4D3AB2A924F233095B6722466428C89F0B8C3271AB1E3F3CBCC0EF411D07F526161502B5729218DFA849ADAF36B834C6F3B4CD1B03A9565DDB1201275FC5BBB7C8BDF12C0AF1FE0A3F3B1F1F4B71C594A010CA4B2C557A5D0C331E88F02A59BD44A1C675F7ECFABD33FE3BB455AE1B08A92D2C2D26841A26B76F6E8C8AC2D21AB24482F4875180F05FFD05BDA8C3381E82F05D07827F42579D4B457F\",\"qinv\":\"D585F456CB8D388A091FF84642AED1950CA62A3D969A8E10F4174AF7E3C3A9762B79775D1646F88A3916762995F33C981EE90F1D5A2B37F44E526611BFE1FB22F217B202EC803350565AA444E899AC4C4F8606C04166D292A107EDB9FAEED55B28F78D218539580D2DEFA493CEACCEF2665E9D94E59BF165288151743A11AF10\",\"dq\":\"911224F6EC3D9228A6BD43ADC3A579B9D55063B55A55BE048B8A743BD365EEC59208182D5C7A9B729E8BB983445EB4F0FA74336578C919A5D1FC4207DF2DC2F912242D140AFB2614002E10A60B159EE91135D5B935E5526BAD5252CB2A4C5ED09B7BFE11FD1468D991888F0583A65A2C8738975211F6297378797C744058C93B\",\"dp\":\"07372075D61AC2BE462C7C517CB595AA44CE96AD3F4AB865DB7BD9FFF8C827BD3C12F9A4A02E6689E53ED9D0AF00EDA37BC22F1FCAC12509F45447485D31AA454E7D610243B7B2856BEA7773766BC293876A78D433F4BFA2BFC57006A912BDB410733B089CFBC9A85F9EEE614E17CE05A165D563BF610845B34002F70FFD359E\"}";

    private RsaPrivateKey key() {
        try {
            return RsaPrivateKey.importFromJson(RsaProviderTests.PRIVATE_KEY_JSON);
        } catch (Exception ex) {
            throw new Error("Unexpected Exception: " + ex.toString());
        }
    }

    @Test
    public void constructor() {
        try {
            new RsaProvider(null);
            fail("A NullPointerException should have been thrown");
        } catch (NullPointerException ex) {}

        try {
            new RsaProvider(this.key());
        } catch (NullPointerException ex) {
            fail("A NullPointerException should not have been thrown");
        }
    }

    @Test
    public void encryptKeyShouldThrowExceptionWhenSymmetricKeyIsTooLong() {
        RsaProvider rp = new RsaProvider(this.key());

        try {
            rp.encryptKey(new byte[1000]);
            fail("An EncryptionException should have been thrown");
        } catch (EncryptionException ex) {}
    }

    @Test
    public void encryptKeyHappyPath() {
        RsaPrivateKey key = this.key();
        RsaProvider rp = new RsaProvider(key);

        try {
            assertEquals(key.byteCount(), rp.encryptKey(new byte[32]).length);
        } catch (EncryptionException ex) {
            fail("Unexpected EncryptionException: " + ex.toString());
        }
    }

    @Test
    public void decryptKeyHappyPath() {
        RsaProvider rp = new RsaProvider(this.key());
        byte[] symmetricKey = new byte[] {1,2,3,4,5,6,7,8};

        try {
            byte[] encryptedKey = rp.encryptKey(symmetricKey);
            assertArrayEquals(symmetricKey, rp.decryptKey(encryptedKey));
        } catch (Exception ex) {
            fail("Unexpected Exception: " + ex.toString());
        }
    }

    @Test
    public void encrypt() {
        RsaPrivateKey key = this.key();
        RsaProvider rp = new RsaProvider(key);

        byte[] data = new byte[] {1,2,3,4};

        int expectedLength = key.byteCount() + ((data.length / 16) + 1) * 16;

        try {
            assertEquals(expectedLength, rp.encrypt(data).length);
        } catch (Exception ex) {
            fail("Unexpected Exception: " + ex.toString());
        }
    }
}

