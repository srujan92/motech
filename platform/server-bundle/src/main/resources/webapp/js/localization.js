/* localization service */

var localizationModule = angular.module('localization', []);

localizationModule.factory("i18nService", function() {
    'use strict';

    var service = {
        ready : false,
        loading : false,
        name : '',
        path : '',

        getMessage : function(key) {
            var msg = '';

            if (this.ready === true) {
                // flatten the arg list and pass to i18n.prop as args
                msg = jQuery.i18n.prop.apply(null, $.map(arguments, function(n) {
                    return n;
                }));
            }

            return msg;
        },

        init : function(lang, name, path, handler) {
            this.ready = false;
            this.loading = true;
            this.name = name;
            this.path = path;

            var self = this;

            jQuery.i18n.properties({
                name: name,
                path: path,
                mode:'map',
                language: lang || null,
                callback: function() {
                    self.ready = true;
                    self.loading = false;
                    if (handler) {
                        handler();
                    }
                }
            });
        }
    };

    return service;
});
