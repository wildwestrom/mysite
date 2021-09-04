module.exports = {
    mode: 'jit',
    darkMode: 'class', // or 'media' or 'class'
    purge: [
        './public/**/*.html',
        './src/**/*.{cljs,clj}',
    ],
    theme: {
        extend: {
            typography: {
                DEFAULT: {
                    css: {
                        pre: {
                            backgroundColor: '#e5e7eb',
                            color: '#1f2937',
                        },
                    },
                },
            },
        },
        nightwind: {
            transitionDuration: "500ms",
            importantNode: true,
            typography: true,
        },
        screens: {
            'xs': {
                'min': '410px'
            },
            'sm': {
                'min': '640px'
            },
            'md': {
                'min': '768px'
            },
            'lg': {
                'min': '1024px'
            },
            'xl': {
                'min': '1280px'
            },
            '2xl': {
                'min': '1536px'
            },
        }
    },
    plugins: [
        require('@tailwindcss/line-clamp'),
        require('@tailwindcss/typography'),
        require('nightwind'),
    ]
}
