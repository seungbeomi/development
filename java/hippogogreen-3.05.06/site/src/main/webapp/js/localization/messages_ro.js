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
 * Locale: RO
 */
jQuery.extend(jQuery.validator.messages, {
  required: "Acest câmp este obligatoriu.",
  remote: "Te rugăm să completezi acest câmp.",
  email: "Te rugăm să introduci o adresă de email validă",
  url: "Te rugăm sa introduci o adresă URL validă.",
  date: "Te rugăm să introduci o dată corectă.",
  dateISO: "Te rugăm să introduci o dată (ISO) corectă.",
  number: "Te rugăm să introduci un număr întreg valid.",
  digits: "Te rugăm să introduci doar cifre.",
  creditcard: "Te rugăm să introduci un numar de carte de credit valid.",
  equalTo: "Te rugăm să reintroduci valoarea.",
  accept: "Te rugăm să introduci o valoare cu o extensie validă.",
  maxlength: jQuery.validator.format("Te rugăm să nu introduci mai mult de {0} caractere."),
  minlength: jQuery.validator.format("Te rugăm să introduci cel puțin {0} caractere."),
  rangelength: jQuery.validator.format("Te rugăm să introduci o valoare între {0} și {1} caractere."),
  range: jQuery.validator.format("Te rugăm să introduci o valoare între {0} și {1}."),
  max: jQuery.validator.format("Te rugăm să introduci o valoare egal sau mai mică decât {0}."),
  min: jQuery.validator.format("Te rugăm să introduci o valoare egal sau mai mare decât {0}.")
});