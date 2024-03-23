FROM node:7-onbuild

LABEL maintainer "swapnil.sarwade8@gmail.com"

HEALTHCHECK --interval=5s \
            --timeout=5s \
            CMD curl -f http://127.0.0.1:3000 || exit 1

EXPOSE 3000
