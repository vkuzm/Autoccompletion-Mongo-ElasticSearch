FROM mongo:5.0

RUN apt-get update && \
    apt-get install -y software-properties-common && \
	apt-add-repository -y ppa:deadsnakes/ppa && \
    apt-get update && \
    apt-get install dos2unix && \
    apt-get install -y --no-install-recommends python3.7 python3-pip && \
	pip install mongo-connector==2.5.1 elastic2-doc-manager[elastic5]
	
WORKDIR /var/tmp/
	
COPY init.sh .
RUN dos2unix init.sh

CMD ["bash", "init.sh"]

EXPOSE 27017