/*
 * Copyright (C) 2010 Hippo B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: NO (Norwegian)
 */
jQuery.extend(jQuery.validator.messages, {
       required: "Dette feltet er obligatorisk.",
       maxlength: jQuery.validator.format("Maksimalt {0} tegn."),
       minlength: jQuery.validator.format("Minimum {0} tegn."),
       rangelength: jQuery.validator.format("Angi minimum {0} og maksimum {1} tegn."),
       email: "Oppgi en gyldig epostadresse.",
       url: "Angi en gyldig URL.",
       date: "Angi en gyldig dato.",
       dateISO: "Angi en gyldig dato (&ARING;&ARING;&ARING;&ARING;-MM-DD).",
       dateSE: "Angi en gyldig dato.",
       number: "Angi et gyldig nummer.",
       numberSE: "Angi et gyldig nummer.",
       digits: "Skriv kun tall.",
       equalTo: "Skriv samme verdi igjen.",
       range: jQuery.validator.format("Angi en verdi mellom {0} og {1}."),
       max: jQuery.validator.format("Angi en verdi som er st&oslash;rre eller lik {0}."),
       min: jQuery.validator.format("Angi en verdi som er mindre eller lik {0}."),
       creditcard: "Angi et gyldig kredittkortnummer."
});