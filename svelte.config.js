import preprocess from 'svelte-preprocess';
import adapter from '@sveltejs/adapter-static';

/** @type {import('@sveltejs/kit').Config} */
const config = {
  // Consult https://github.com/sveltejs/svelte-preprocess
  // for more information about preprocessors
  preprocess: [
    preprocess({
      postcss: true
    })
  ],

  kit: {
    // hydrate the <div id="svelte"> element in src/app.html
    target: '#svelte',
    adapter: adapter({
      pages: 'build',
      assets: 'build',
      fallback: null
    }),
    vite: {
      ssr: {
        noExternal: [
          'uniorg',
          'uniorg-extract-keywords',
          'uniorg-parse',
          'uniorg-rehype',
          'rehype-highlight',
          'rehype-raw',
          'rehype-stringify',
          'unified'
        ],
        optimizeDeps: {
          exclude: [
            'uniorg',
            'uniorg-extract-keywords',
            'uniorg-parse',
            'uniorg-rehype',
            'rehype-highlight',
            'rehype-raw',
            'rehype-stringify',
            'unified'
          ]
        }
      }
    }
  }
};

export default config;
