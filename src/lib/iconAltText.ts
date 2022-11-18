function getEnd(icon: string): string {
	if (icon.endsWith('-alt')) {
		return icon.split('-')[0];
	} else {
		return icon;
	}
}

function toTitleCase(str: string): string {
	return str.replace(/\w\S*/g, function (txt) {
		return txt.charAt(0).toUpperCase() + txt.substring(1).toLowerCase();
	});
}

export default function iconAltText(icon: string): string {
	return toTitleCase(getEnd(icon));
}
