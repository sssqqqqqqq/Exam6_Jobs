const CryptoJS = require("crypto-js");

module.exports = {

    isEmail(mail) {
        var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if (filter.test(mail)) {
            return true; 
        } else {
            return false;
        } 
    },

    aesTranslate(passowrd){
        var key = 'lpzsygdsgtsl9908';
        var iv = 'lpzsygdsgtsl9908';
        key = CryptoJS.enc.Utf8.parse(key);
        iv = CryptoJS.enc.Utf8.parse(iv);  
        var encrypted = CryptoJS.AES.encrypt(passowrd, key, {
            iv: iv,
            mode: CryptoJS.mode.CBC,
            padding: CryptoJS.pad.Pkcs7
        });
        
        // 转换为字符串
        encrypted = encrypted.toString();
        return encrypted;
    },

    passwordTanslate(passowrd){
        var key = 'lpzsygdsgtsl9908';
        var iv = 'lpzsygdsgtsl9908';
        
        var decrypted = CryptoJS.AES.decrypt(passowrd, key, {
            iv: iv,
            mode: CryptoJS.mode.CBC,
            padding: CryptoJS.pad.Pkcs7
        });
         
        // 转换为 utf8 字符串
        decrypted = CryptoJS.enc.Utf8.stringify(decrypted);
        return decrypted;
    }

}