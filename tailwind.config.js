module.exports = {
    purge: [
        './src/**/*.cljs',
        './src/**/*.clj'
    ],
    darkMode: 'media', // or 'media' or 'class'
    theme: {},
    variants: {
        extend: {
            textColor: ['visited'],
        }
    },
    plugins: []
}
