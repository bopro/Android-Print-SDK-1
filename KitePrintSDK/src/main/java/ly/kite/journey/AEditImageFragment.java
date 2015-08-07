/*****************************************************
 *
 * AEditImageFragment.java
 *
 *
 * Modified MIT License
 *
 * Copyright (c) 2010-2015 Kite Tech Ltd. https://www.kite.ly
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The software MAY ONLY be used with the Kite Tech Ltd platform and MAY NOT be modified
 * to be used with any competitor platforms. This means the software MAY NOT be modified 
 * to place orders with any competitors to Kite Tech Ltd, all orders MUST go through the
 * Kite Tech Ltd platform servers. 
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 *****************************************************/

///// Package Declaration /////

package ly.kite.journey;


///// Import(s) /////

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import ly.kite.R;
import ly.kite.product.Product;
import ly.kite.widget.EditableConsumerImageView;


///// Class Declaration /////

/*****************************************************
 *
 * This activity allows the user to create a phone
 * case design using an image.
 *
 *****************************************************/
abstract public class AEditImageFragment extends AKiteFragment implements View.OnClickListener
  {
  ////////// Static Constant(s) //////////

  @SuppressWarnings( "unused" )
  private static final String      LOG_TAG                        = "AEditImageFragment";

  public  static final String      BUNDLE_KEY_PRODUCT             = "product";


  ////////// Static Variable(s) //////////


  ////////// Member Variable(s) //////////

  protected Product                       mProduct;

  protected EditableConsumerImageView     mEditableConsumerImageView;
  protected Button                        mCancelButton;
  protected Button                        mConfirmButton;


  ////////// Static Initialiser(s) //////////


  ////////// Static Method(s) //////////


  ////////// Constructor(s) //////////


  ////////// Activity Method(s) //////////

  /*****************************************************
   *
   * Called when the activity is created.
   *
   *****************************************************/
  @Override
  public void onCreate( Bundle savedInstanceState )
    {
    super.onCreate( savedInstanceState );


    // Get the assets and product

    Bundle arguments = getArguments();

    if ( arguments == null )
      {
      Log.e( LOG_TAG, "No arguments found" );

      mKiteActivity.displayModalDialog(
              R.string.alert_dialog_title_no_arguments,
              R.string.alert_dialog_message_no_arguments,
              AKiteActivity.DONT_DISPLAY_BUTTON,
              null,
              R.string.Cancel,
              mKiteActivity.new FinishRunnable()
      );

      return;
      }


    mProduct = arguments.getParcelable( BUNDLE_KEY_PRODUCT );

    if ( mProduct == null )
      {
      Log.e( LOG_TAG, "No product found" );

      mKiteActivity.displayModalDialog(
              R.string.alert_dialog_title_product_not_found,
              R.string.alert_dialog_message_product_not_found,
              AKiteActivity.DONT_DISPLAY_BUTTON,
              null,
              R.string.Cancel,
              mKiteActivity.new FinishRunnable()
      );

      return;
      }

    }


  /*****************************************************
   *
   * Returns the content view for this fragment
   *
   *****************************************************/
  @Override
  public View onCreateView( LayoutInflater layoutInflator, ViewGroup container, Bundle savedInstanceState )
    {
    View view = layoutInflator.inflate( R.layout.screen_edit_image, container, false );

    mEditableConsumerImageView = (EditableConsumerImageView)view.findViewById( R.id.editable_consumer_image_view );
    mCancelButton              = (Button)view.findViewById( R.id.cancel_button );
    mConfirmButton             = (Button)view.findViewById( R.id.confirm_button );


    mCancelButton.setOnClickListener( this );
    mConfirmButton.setOnClickListener( this );


    return ( view );
    }


  ////////// View.OnClickListener Method(s) //////////

  /*****************************************************
   *
   * Called when something is clicked.
   *
   *****************************************************/
  @Override
  public void onClick( View view )
    {
    if ( view == mCancelButton )
      {
      ///// Cancel /////

      onCancel();
      }
    else if ( view == mConfirmButton )
      {
      ///// Confirm /////

      onConfirm();
      }
    }


  ////////// Method(s) //////////

  /*****************************************************
   *
   * Called when the cancel button is clicked.
   *
   *****************************************************/
  protected void onCancel()
    {
    }


  /*****************************************************
   *
   * Called when the confirm button is clicked.
   *
   *****************************************************/
  abstract protected void onConfirm();


  ////////// Inner Class(es) //////////

  }
