import { Link } from "react-router-dom";

import React from "react";
import {socialMediaLinks} from "../../utils/static/social-media-links.jsx";

export default function Socials({ componentsClass, textSize }) {
    return (
        <>
            {socialMediaLinks.map((link, index) => (
                <a
                    key={index}
                    className={componentsClass ? componentsClass : ""}
                    href={link.href}
                >
                    <i className={`${link.iconClassName} ${textSize}`}></i>
                </a>
            ))}
        </>
    );
}
