module.exports = {
    purge: false,
    darkMode: 'media', // or 'media' or 'class'
    theme: {
        screens: {
            'xs': {'max': '400px'},
            'sm': {'max': '640px'},
            'md': {'max': '768px'},
            'lg': {'max': '1024px'},
            'xl': {'max': '1280px'},
            '2xl':{'max': '1536px'},
        }
    },
    variants: {
        extend: {
            textColor: ['visited'],
        }
    },
    plugins: [
        require('@tailwindcss/line-clamp'),
    ]
}
