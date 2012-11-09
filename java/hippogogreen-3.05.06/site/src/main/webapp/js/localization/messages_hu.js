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
 * Locale: HU
 */
jQuery.extend(jQuery.validator.messages, {
	required: "Kötelező megadni.",
	maxlength: jQuery.validator.format("Legfeljebb {0} karakter hosszú legyen."),
	minlength: jQuery.validator.format("Legalább {0} karakter hosszú legyen."),
	rangelength: jQuery.validator.format("Legalább {0} és legfeljebb {1} karakter hosszú legyen."),
	email: "Érvényes e-mail címnek kell lennie.",
	url: "Érvényes URL-nek kell lennie.",
	date: "Dátumnak kell lennie.",
	number: "Számnak kell lennie.",
	digits: "Csak számjegyek lehetnek.",
	equalTo: "Meg kell egyeznie a két értéknek.",
	range: jQuery.validator.format("{0} és {1} közé kell esnie."),
	max: jQuery.validator.format("Nem lehet nagyobb, mint {0}."),
	min: jQuery.validator.format("Nem lehet kisebb, mint {0}."),
	creditcard: "Érvényes hitelkártyaszámnak kell lennie."
});
