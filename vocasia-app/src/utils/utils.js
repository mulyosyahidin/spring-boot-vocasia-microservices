import {apiBaseUrl, AUTH_USER, AWS_BUCKET_NAME, AWS_REGION} from "../config/consts.js";
import moment from "moment";

const _api = ({ endpoint, replaces = [] }) => {
    let replacedEndpoint = endpoint;

    replaces.forEach((replace) => {
        replacedEndpoint = replacedEndpoint.replace(/:[^/]+/, replace);
    });

    return `${apiBaseUrl}${replacedEndpoint}`;
};

const getLocalStorageItem = (key) => {
    const item = localStorage.getItem(key);

    try {
        return item ? JSON.parse(item) : null;
    } catch (error) {
        console.error(`Error parsing localStorage item "${key}":`, error);
        return null;
    }
}

const generateAWSObjectUrl = (key) => {
    const bucketName = AWS_BUCKET_NAME;
    const region = AWS_REGION;

    return `https://${bucketName}.s3.${region}.amazonaws.com/${key}`;
}

const getYouTubeVideoId = (url) => {
    const urlObject = new URL(url);
    const searchParams = new URLSearchParams(urlObject.search);

    return searchParams.get('v');
}

const rupiahFormatter = new Intl.NumberFormat('id-ID', {
    style: 'currency',
    currency: 'IDR',
    minimumFractionDigits: 0
});

const getPercentage = (total, discount) => {
    return Math.round((discount / total) * 100);
}

const makeDateReadable = ({date, format = 'DD MMMM YYYY HH:mm'}) => {
    let parseDate = moment(date);

    return parseDate.format(format);
}

const getUserProfilePictureUrl = () => {
    let userData = localStorage.getItem(AUTH_USER);
    userData = JSON.parse(userData);

    return userData.profile_picture_url ? userData.profile_picture_url : "/assets/img/misc/user-profile.png";
}

export { _api, getLocalStorageItem, generateAWSObjectUrl, getYouTubeVideoId, rupiahFormatter, getPercentage, makeDateReadable, getUserProfilePictureUrl };