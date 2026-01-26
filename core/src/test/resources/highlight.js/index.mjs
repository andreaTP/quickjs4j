// written by the user

import hljs from 'highlight.js';

export function highlight() {
    const html = hljs.highlight('<h1>Hello World!</h1>', {language: 'xml'}).value;

    return html;
}
