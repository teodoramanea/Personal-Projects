
import { RouteObject, createBrowserRouter } from "react-router-dom";
import { Login } from "../pages/login/login";
import {Profile} from "../pages/profile/profile";
import { SignUp } from "../pages/signUp/signUp";
import {EditProfile} from "../pages/editProfile/editProfile";
import {QuizzPage} from "../pages/QuizzPage/QuizzPage";
import {Admin} from "../pages/admin/admin";

const routes: RouteObject[] = [
    {
        path: "/",
        element: <Login />
    },
    {
        path: "/profile",
        element: <Profile />
    },
    {
        path: "/signUp",
        element: < SignUp />
    },
    {
        path: "/editProfile",
        element: < EditProfile />
    },
    {
        path:"/QuizzPage",
        element: < QuizzPage />
    },
    {
        path:"/admin",
        element: < Admin />
    }

];

export const router = createBrowserRouter(routes)