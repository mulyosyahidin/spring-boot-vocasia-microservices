import {selector} from 'recoil';
import {authAtom} from "../Atoms/Auth.jsx";

export const authStatusSelector = selector({
    key: 'authStatusSelector',
    get: ({ get }) => {
        const auth = get(authAtom);

        return {
            isLoggedIn: !!auth.accessToken,
            user: auth.user,
        };
    },
});

export const tokensSelector = selector({
    key: 'tokensSelector',
    get: ({get}) => {
        const auth = get(authAtom);

        return {
            accessToken: auth.accessToken,
            refreshToken: auth.refreshToken,
        };
    },
});
