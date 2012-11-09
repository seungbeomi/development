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
 * Locale: IT
 */
jQuery.extend(jQuery.validator.messages, {
       required: "Campo obbligatorio.",
       remote: "Controlla questo campo.",
       email: "Inserisci un indirizzo email valido.",
       url: "Inserisci un indirizzo web valido.",
       date: "Inserisci una data valida.",
       dateISO: "Inserisci una data valida (ISO).",
       number: "Inserisci un numero valido.",
       digits: "Inserisci solo numeri.",
       creditcard: "Inserisci un numero di carta di credito valido.",
       equalTo: "Il valore non corrisponde.",
       accept: "Inserisci un valore con un&apos;estensione valida.",
       maxlength: jQuery.validator.format("Non inserire pi&ugrave; di {0} caratteri."),
       minlength: jQuery.validator.format("Inserisci almeno {0} caratteri."),
       rangelength: jQuery.validator.format("Inserisci un valore compreso tra {0} e {1} caratteri."),
       range: jQuery.validator.format("Inserisci un valore compreso tra {0} e {1}."),
       max: jQuery.validator.format("Inserisci un valore minore o uguale a {0}."),
       min: jQuery.validator.format("Inserisci un valore maggiore o uguale a {0}.")
});