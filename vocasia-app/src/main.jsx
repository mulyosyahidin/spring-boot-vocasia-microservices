import './styles/index.scss';

import React from 'react'
import ReactDOM from 'react-dom/client'
import {RouterProvider} from "react-router-dom";
import {RecoilRoot} from "recoil";
import routes from "./routes/Index.jsx";

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <RecoilRoot>
            <RouterProvider router={routes} />
        </RecoilRoot>
    </React.StrictMode>,
)
