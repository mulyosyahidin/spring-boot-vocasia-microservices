import React from "react"
import {Link} from "react-router-dom";

const links = [
    {id: 1, href: "/help-center", label: "Help"},
    {id: 2, href: "/terms", label: "Privacy Policy"},
    {id: 3, href: "/terms", label: "Cookie Notice"},
    {id: 4, href: "/terms", label: "Security"},
    {id: 5, href: "/terms", label: "Terms of Use"},
];

export const Footer = () => {
    return (
        <footer className="footer">
            <div className="container">
                <div className="py-30 border-top-light">
                    <div className="row items-center justify-between">
                        <div className="col-auto">
                            <div className="text-13 lh-1">
                                © {new Date().getFullYear()} Educrat. All Right Reserved.
                            </div>
                        </div>

                        <div className="col-auto">
                            <div className="d-flex items-center">
                                <button className="button -md -rounded bg-light-4 text-light-1 ml-30">
                                    English
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    );
}
