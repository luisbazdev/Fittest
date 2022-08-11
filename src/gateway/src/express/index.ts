import express, { Express } from "express";
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
app.use(cookieParser())

app.use("/auth/info", isAuthenticatedMiddleware, proxy("http://localhost:1234", {
    proxyReqPathResolver: function(req) {
        return `/api${req.originalUrl}`
    }
}));
app.use("/auth", proxy('http://localhost:1234', {
    proxyReqPathResolver: function(req) {
        return `/api${req.originalUrl}`
    }
}));
app.use("/routine", isAuthenticatedMiddleware, proxy('http://localhost:1235', {
    proxyReqPathResolver: function(req) {
        return `/api${req.originalUrl}`
    }
}));
app.use("/workout", isAuthenticatedMiddleware, proxy('http://localhost:1236', {
    proxyReqPathResolver: function(req) {
        return `/api${req.originalUrl}`
    }
}));
app.use("/schedule", isAuthenticatedMiddleware, proxy('http://localhost:1237', {
    proxyReqPathResolver: function(req) {
        return `/api${req.originalUrl}`
    }
}));
