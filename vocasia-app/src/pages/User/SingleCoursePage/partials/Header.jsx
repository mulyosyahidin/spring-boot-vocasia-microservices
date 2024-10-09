import {Link} from "react-router-dom";

export const Header = ({title}) => {
    return (
        <header className="header -type-1 js-header">
            <div className="header__container">
                <div className="row justify-between items-center">
                    <div className="col-auto">
                        <div className="header-left">
                            <div className="header__logo">
                                <Link data-barba to="/users">
                                    <img src="/logo-white.svg" alt="logo"/>
                                </Link>
                            </div>
                        </div>
                    </div>

                    <div className="col-auto lg:d-none">
                        <div className="text-20 lh-1 text-white fw-500">
                            {title}
                        </div>
                    </div>

                    <div className="col-auto">
                        <div className="header-right d-flex items-center">
                            <div className="header-right__buttons">
                                <Link to={'/users/courses'} className="button -sm -rounded -white text-dark-1">
                                    Kembali
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    )
}
