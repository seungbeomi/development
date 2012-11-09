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
 * Locale: CS
 */
jQuery.extend(jQuery.validator.messages, {
	required: "Tento údaj je povinný.",
	remote: "Prosím, opravte tento údaj.",
	email: "Prosím, zadejte platný e-mail.",
	url: "Prosím, zadejte platné URL.",
	date: "Prosím, zadejte platné datum.",
	dateISO: "Prosím, zadejte platné datum (ISO).",
	number: "Prosím, zadejte číslo.",
	digits: "Prosím, zadávejte pouze číslice.",
	creditcard: "Prosím, zadejte číslo kreditní karty.",
	equalTo: "Prosím, zadejte znovu stejnou hodnotu.",
	accept: "Prosím, zadejte soubor se správnou příponou.",
	maxlength: jQuery.validator.format("Prosím, zadejte nejvíce {0} znaků."),
	minlength: jQuery.validator.format("Prosím, zadejte nejméně {0} znaků."),
	rangelength: jQuery.validator.format("Prosím, zadejte od {0} do {1} znaků."),
	range: jQuery.validator.format("Prosím, zadejte hodnotu od {0} do {1}."),
	max: jQuery.validator.format("Prosím, zadejte hodnotu menší nebo rovnu {0}."),
	min: jQuery.validator.format("Prosím, zadejte hodnotu větší nebo rovnu {0}.")
});
