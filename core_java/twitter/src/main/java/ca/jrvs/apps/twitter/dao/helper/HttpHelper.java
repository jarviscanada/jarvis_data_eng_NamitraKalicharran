package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public interface HttpHelper {

  /**
   * Execute a HTTP Post call
   * @param uri
   * @return
   * @throws OAuthCommunicationException
   * @throws OAuthExpectationFailedException
   * @throws OAuthMessageSignerException
   */
  HttpResponse httpPost(URI uri) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException;

  /**
   * Execute a HTTP Get call
   * @param uri
   * @return
   */
  HttpResponse httpGet(URI uri);
}
