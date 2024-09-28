import {apiBaseUrl, AWS_BUCKET_NAME, AWS_REGION} from "../config/consts.js";

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

export { _api, getLocalStorageItem, generateAWSObjectUrl };