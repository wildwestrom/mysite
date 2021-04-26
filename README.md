# Shadow-Cljs/Reagent Site Generator
This is a static site generator mostly based off of [shadow-static](https://github.com/yosevu/shadow-static "shadow-static by yosevu").
It has the nice feature of leveraging tailwindcss for ease of styling.
There's not a lot implemented here, so I don't recommend using this for an actual blog.

Add all your Metadata to `src/app/data.cljc`.

## Develop

Before running for the first time run:
`yarn install`

### CLI

`yarn start`

### [CIDER](https://cider.mx/) (Emacs)

- `cider-jack-in-cljs`

If using the cider-connect-sibling-cljs option:

-  Select the **shadow** 
-  Select **:app**

Visit 'http://localhost:3000' in a browser? (y or n)

- Enter `y`

## Release

`yarn release`

See [Generating Production Code](https://shadow-cljs.github.io/docs/UsersGuide.html#release) for more information.

## License

This is free software licensed under the [GNU AGPL license](https://www.gnu.org/licenses/agpl-3.0.html).
