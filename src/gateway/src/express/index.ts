import express, { Express, Request } from "express";
import proxy from "express-http-proxy";
import cors, { CorsOptions } from "cors";
import cookieParser from "cookie-parser";
import { isAuthenticatedMiddleware } from "./middleware/isAuthenticated";

export const app: Express = express();

const options: CorsOptions = {
    origin: "http://localhost:5173",
    methods: ["GET", "POST"],
    credentials: true
}

app.use(cors(options));
app.use(cookieParser());

function resolver(req: Request){
    return `/api${req.originalUrl}`
}

app.use("/auth/info", isAuthenticatedMiddleware, proxy("http://localhost:1234", {
    proxyReqPathResolver: resolver
}));
app.use("/auth", proxy('http://localhost:1234', {
    proxyReqPathResolver: resolver
}));
app.use("/routine", isAuthenticatedMiddleware, proxy('http://localhost:1235', {
    proxyReqPathResolver: resolver
}));
app.use("/workout", isAuthenticatedMiddleware, proxy('http://localhost:1236', {
    proxyReqPathResolver: resolver
}));
app.use("/schedule", isAuthenticatedMiddleware, proxy('http://localhost:1237', {
    proxyReqPathResolver: resolver
}));
