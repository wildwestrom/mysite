import katex from 'katex';

export function load() {
	return {
		gInfinity: katex.renderToString('G^{\\infty}', { throwOnError: false })
	};
}
