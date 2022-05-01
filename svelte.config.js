import preprocess from "svelte-preprocess";
import adapter from "@sveltejs/adapter-static";

const config = {
  // Consult https://github.com/sveltejs/svelte-preprocess
  // for more information about preprocessors
  preprocess: [
    preprocess({
      postcss: true,
    }),
  ],

  kit: {
    prerender: {
      default: true,
    },
    // hydrate the <div id="svelte"> element in src/app.html
    adapter: adapter({
      pages: "build",
      assets: "build",
      fallback: null,
    }),
  },
};

export default config;
